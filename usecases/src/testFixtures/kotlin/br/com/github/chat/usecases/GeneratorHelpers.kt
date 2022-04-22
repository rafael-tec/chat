package br.com.github.chat.usecases

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next
import io.kotest.property.arbitrary.string

fun string() = Arb.string().next()

fun int() = Arb.int().next()

