import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import "antd/dist/antd.css";
import store from "./store";

import { Provider } from "react-redux";

// ReactDOM.render(<App />, document.querySelector("#root"));
ReactDOM.render(
    <Provider store={store}>
      <App />
    </Provider>,
    document.querySelector("#root")
  );