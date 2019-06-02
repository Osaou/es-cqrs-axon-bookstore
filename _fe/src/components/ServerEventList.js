import React from 'react'
import { connect } from 'react-redux'

const ServerEventList = ({ messages }) => {
  return (
    <ul>
      {messages.map(msg =>
        <li key={msg.id}>
          {msg.evt}: {msg.name}
        </li>
      )}
    </ul>
  )
}

const mapStateToProps = state => {
  return {
    messages: state.messages
  }
}

export default connect(mapStateToProps)(ServerEventList)
