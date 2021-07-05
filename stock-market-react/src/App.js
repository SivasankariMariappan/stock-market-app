import React, { Component } from "react"
import "./App.css"
import { Provider } from "react-redux"
import DashboardComponent from "./components/dashboard-component"
import getAppStore from "./store/store"

const store = getAppStore()

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <DashboardComponent />
      </Provider>
    )
  }
}

export default App
