import React, { Component } from "react";

class FormContainer extends Component {
  constructor() {
    super();
    this.state = {
      title: ""
    };
  }

  render() {
    return (
      <React.Fragment>
        <form id="impersonator-form" />
      </React.Fragment>
    );
  }
}

export default FormContainer;
