import React, { useState } from "react";
import { Button, Modal, Form } from "semantic-ui-react";
import { connect } from "react-redux";
import Slider from "rc-slider";
import "rc-slider/assets/index.css";
import Hashtag from "./Hashtag";
import axios from "axios";

const handle = (smileys, stopPoints) => {
  return (props) => {
    const style = {
      left: `${props.offset}%`,
    };
    let index = stopPoints.findIndex((threshold) => props.value < threshold);
    const smiley = smileys[index];

    return (
      <div style={style} className="smiley-handle">
        {smiley}
        <label style={{ fontSize: "20px", marginLeft: "10px" }}>
          {index + 1}
        </label>
      </div>
    );
  };
};

class SmileySlider extends React.PureComponent {
  // constructor(props){
  //   super(props)
  //   this.prio
  // }
  render() {
    const sliderHandle = handle(this.props.smileys, this.props.stopPoints);
    return (
      <Slider
        min={this.props.minValue}
        max={this.props.maxValue}
        defaultValue={this.props.defaultVal}
        handle={sliderHandle}
        step={this.props.step || 1}
        onAfterChange={this.props.handleChange}
        onChange={(e) => {
          this.props.setRate(e);
        }}
      />
    );
  }
}

class Sliders extends React.PureComponent {
  render() {
    return (
      <div className="text-center">
        <SmileySlider
          smileys={[
            String.fromCodePoint(128557),
            String.fromCodePoint(128546),
            String.fromCodePoint(128542),
            String.fromCodePoint(128528),
            String.fromCodePoint(128578),
            String.fromCodePoint(128515),
            String.fromCodePoint(128522),
            String.fromCodePoint(128516),
            String.fromCodePoint(128519),
            String.fromCodePoint(128525),
          ]}
          stopPoints={[2, 3, 4, 5, 6, 7, 8, 9, 10, 11]}
          minValue={1}
          maxValue={10}
          defaultVal={1}
          setRate={this.props.setRate}
        />
      </div>
    );
  }
}

function FormComponent(props) {
  const [open, setOpen] = React.useState(false);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [rating, setRating] = useState(1);
  const [moodJustification, setMoodJustification] = useState("");
  const [hashtag, setHashtag] = useState("");
  // console.log(rating);
  const ref = React.createRef();

  async function postData() {
    let hashtag = props.exampleReducer.value;

    var today = new Date();
    var date =
      today.getFullYear() +
      "-" +
      (today.getMonth() + 1) +
      "-" +
      today.getDate();
    var time =
      today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

    var dateTime = date + " " + time;

    const formData = {
      dateTime,
      name,
      email,
      rating,
      moodJustification,
      hashtag,
    };
    console.log(formData);
    await axios.post("https://grads-coding-challenge-group-4.uc.r.appspot.com/saveMoodHistory", formData);
  }
  return (
    <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={<Button>Rate your Mood</Button>}
    >
      <Modal.Header style={{ fontFamily: "Roboto Slab" }}>
        Submit your Mood Rating
      </Modal.Header>
      <Form>
        <Form.Field style={{ width: "90%", margin: "10px auto" }}>
          <label style={{ fontFamily: "Verdana" }}>Rate</label>
          <Sliders setRate={setRating}></Sliders>
        </Form.Field>

        <div className="two fields">
          <Form.Field
            style={{
              width: "40%",
              margin: "15px",
              marginLeft: "auto",
              marginRight: "auto",
            }}
          >
            <label style={{ fontFamily: "Verdana" }}>Name</label>
            <input
              placeholder="Name"
              onChange={(e) => setName(e.target.value)}
            />
          </Form.Field>
          <Form.Field
            style={{
              width: "40%",
              margin: "15px",
              marginLeft: "auto",
              marginRight: "auto",
            }}
          >
            <label style={{ fontFamily: "Verdana" }}>Email ID</label>
            <input
              placeholder="Email ID"
              onChange={(e) => setEmail(e.target.value)}
            />
          </Form.Field>
        </div>

        <Form.Field style={{ width: "90%", margin: "15px auto" }}>
          <label style={{ fontFamily: "Verdana" }}>Rating Justification</label>
          <textarea
            placeholder="Justify your Rating"
            onChange={(e) => setMoodJustification(e.target.value)}
          />
        </Form.Field>
        <Form.Field style={{ width: "90%", margin: "15px auto" }}>
          <label style={{ fontFamily: "Verdana" }}>Hashtags</label>
          <Hashtag />
        </Form.Field>
      </Form>
      <Modal.Actions>
        <Button color="black" onClick={() => setOpen(false)}>
          Cancel
        </Button>
        <Button
          content="Submit"
          labelPosition="right"
          icon="checkmark"
          onClick={(e) => {
            setOpen(false);
            postData();
          }}
          positive
        />
      </Modal.Actions>
    </Modal>
  );
}

const mapStateToProps = (state) => {
  return {
    exampleReducer: state.exampleReducer,
  };
};
export default connect(mapStateToProps)(FormComponent);
