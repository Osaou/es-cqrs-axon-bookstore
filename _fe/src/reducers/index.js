import { SERVER_EVENT_MESSAGE } from '../utils/serverEventBus'

const initialState = {
    messages: []
}

function rootReducer(state = initialState, action) {
    switch (action.type) {
        case SERVER_EVENT_MESSAGE:
            return {
                messages: [...state.messages, action.data]
            }

        default:
            return state
    }
}

export default rootReducer
