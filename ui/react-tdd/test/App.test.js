import React from 'react';
import {shallow} from 'enzyme';
import App from 'src/App';
import FormContainer from 'src/js/container/FormContainer'

describe('App', () => {

    let wrapper;
    let instance;

    beforeEach(() => {
        wrapper = shallow(<App/>);
        instance = wrapper.instance();
    })    

    it('should render child elements', () => {
        expect(wrapper.find(FormContainer)).toHaveLength(1);
    })
});