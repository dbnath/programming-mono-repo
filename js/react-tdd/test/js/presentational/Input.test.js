import { mount } from "enzyme";
import React from "react";
import Input from "src/js/presentational/Input";

describe("Input presentational component", () => {
  const props = {
    label: "URL",
    width: 50,
    text: "Enter URL to impersonate",
    type: "text",
    id: "targetUrl",
    value: ""
  };
  it("should load with props", () => {
    let changeHandler = event => {
      console.log("Handled change->", event);
    };
    const wrapper = mount(<Input {...props} handleChange={changeHandler}/>);
    expect(wrapper.length).toEqual(1);
  });
});
