import React, { Component } from "react";
import FormContainer from "../src/js/container/FormContainer";

export default class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <React.Fragment>
        <FormContainer />
      </React.Fragment>
    );
  }
}
