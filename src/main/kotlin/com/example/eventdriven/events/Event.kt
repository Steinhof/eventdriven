package com.example.eventdriven.events

import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.UUID


interface Event {
    val id: UUID
    val initiator: String
    val data: ObjectNode?
}