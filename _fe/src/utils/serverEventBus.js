export const SERVER_EVENT_CONNECT = 'SERVER_EVENT_CONNECT'
export const SERVER_EVENT_MESSAGE = 'SERVER_EVENT_MESSAGE_RECEIVED'

const eventBus = (reduxStore) => {
    const ws = new WebSocket('ws://localhost:8000/events')

    ws.addEventListener('open', e => {
        console.log('connected to server!')
        reduxStore.dispatch({ type: SERVER_EVENT_CONNECT })
    })
    ws.addEventListener('message', e => {
        console.log('message from server: ', e.data)
        const json = JSON.parse(e.data)
        reduxStore.dispatch({ type: SERVER_EVENT_MESSAGE, data: json })
    })
    ws.addEventListener('error', e => {
        console.log('ws error: ', e)
    })

    return function cleanup() {
        ws.close()
    }
}

export default eventBus
