import Enzyme, {mount, render, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import renderer from 'react-test-renderer';
import React from 'react';

Enzyme.configure({adapter: new Adapter()})

global.shallow = shallow;
global.render = render;
global.mount = mount;
global.renderer = renderer;

global.React = React;

global.window.alert = () => {}