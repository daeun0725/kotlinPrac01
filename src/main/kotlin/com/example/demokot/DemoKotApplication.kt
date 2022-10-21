package com.example.demokot

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@SpringBootApplication
@EnableJpaAuditing
class DemoKotApplication{

    @Bean
    fun jpaQueryFactory(em: EntityManager) = JPAQueryFactory(em)

}

fun main(args: Array<String>) {
    runApplication<DemoKotApplication>(*args)
}
