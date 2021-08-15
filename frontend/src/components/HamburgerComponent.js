import React from "react";
import Hamburger from "hamburger-react";
import { connect } from "react-redux";
import { toggleHamburger } from "../actions";

const HamburgerComponent = (props) => {
  // console.log(props);
  return <Hamburger toggled={props.isOpen} toggle={props.toggleHamburger} />;
};

const mapStateToProps = (state) => {
  return {
    isOpen: state.isOpen,
  };
};
export default connect(mapStateToProps, { toggleHamburger })(
  HamburgerComponent
);
