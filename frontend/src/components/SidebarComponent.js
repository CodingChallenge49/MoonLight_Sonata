import React from "react";
import {
  Grid,
  Icon,
  Menu,
  Segment,
  Sidebar,
} from "semantic-ui-react";
import { connect } from "react-redux";
import { isVisible, toggleHamburger } from "../actions";
import HamburgerComponent from "./HamburgerComponent";
import LiveFeed from "./LiveFeed";
import FormComponent from "./FormComponent";
import Summary from "./Summary";

const SidebarComponent = (props) => {
  console.log(props);
  let display = <LiveFeed />;
  if (props.selectedNavOption === "live") {
    display = <LiveFeed />;
  } else if (props.selectedNavOption === "dashboard") {
    display = <Summary />;
  } else {
    display = <FormComponent />;
  }
  return (
    <div>
      <Grid columns={1} style={{ height: "120vh" }}>
        <Grid.Column>
          <Sidebar.Pushable as={Segment}>
            <Sidebar
              as={Menu}
              animation="overlay"
              icon="labeled"
              inverted
              onHide={() => props.toggleHamburger(false)}
              vertical
              visible={props.isOpen}
              width="thin"
            >
              <Menu.Item
                as="a"
                onClick={() => {
                  props.isVisible("live");
                  props.toggleHamburger(false);
                }}
              >
                <Icon name="feed" />
                Live Feed
              </Menu.Item>
              <Menu.Item
                as="a"
                onClick={() => {
                  props.isVisible("dashboard");
                  props.toggleHamburger(false);
                }}
              >
                <Icon name="dashboard" />
                Dashboard
              </Menu.Item>
              <Menu.Item
                as="a"
                onClick={() => {
                  props.isVisible("rate");
                  props.toggleHamburger(false);
                }}
              >
                <Icon name="upload" />
                Rate your Mood
              </Menu.Item>
            </Sidebar>
            <Sidebar.Pusher dimmed={props.isOpen}>
              <Segment basic>
                <Grid centered columns={2}>
                  <Grid.Column width={1}>
                    <HamburgerComponent />
                  </Grid.Column>
                  <Grid.Column width={15}>{display}</Grid.Column>
                </Grid>
              </Segment>
            </Sidebar.Pusher>
          </Sidebar.Pushable>
        </Grid.Column>
      </Grid>
    </div>
  );
};

const mapStateToProps = (state) => {
  return {
    isOpen: state.isOpen,
    selectedNavOption: state.selectedNavOption,
  };
};
export default connect(mapStateToProps, {
  toggleHamburger: toggleHamburger,
  isVisible: isVisible,
})(SidebarComponent);
