package com.example.eventdriven.events

import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.UUID

class CreateApplicationExecute(
    override val id: UUID,
    override val initiator: String,
    override val data: ObjectNode?
) : Event