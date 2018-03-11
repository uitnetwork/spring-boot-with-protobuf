package com.uitnetwork.demo.config

import akka.actor.*
import com.uitnetwork.demo.actor.PersonActor
import com.uitnetwork.proto.PersonProto
import mu.KotlinLogging
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class AkkaActorConfig(private val applicationContext: ApplicationContext) {
    companion object {
        const val ACTOR_SYSTEM = "a-system"
    }

    private val logger = KotlinLogging.logger { }

    @Bean
    fun actorSystem(): ActorSystem {
        return ActorSystem.create(ACTOR_SYSTEM)
    }

    @PostConstruct
    fun testSpringActorIntegration() {
        val person = PersonProto.Person.newBuilder().setName("Just a test").build()
        val test1 = actorSystem().actorOf(Props.create(SpringActorProducer::class.java, applicationContext, PersonActor::class.java),
                "test1")

        logger.info { "ref test1: $test1" }
        test1.tell(person, ActorRef.noSender())

        val test2 = actorSystem().actorOf(Props.create(SpringActorProducer::class.java, applicationContext, PersonActor::class.java),
                "test2")
        logger.info { "ref test2: $test2" }
        test2.tell(person, ActorRef.noSender())
    }
}

class SpringActorProducer(private val applicationContext: ApplicationContext,
                          private val beanType: Class<out Actor>)
    : IndirectActorProducer {

    override fun produce(): Actor = applicationContext.getBean(beanType)

    override fun actorClass(): Class<out Actor> = beanType
}
