import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Grid } from "semantic-ui-react";
import { fetchLiveFeed } from "../actions";

const smileys = [
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
];

const LiveFeed = (props) => {
  useEffect(() => {
    props.fetchLiveFeed();
  }, []);
  return (
    <div>
      <h1 style={{ textAlign: "center" }}>Live Feed</h1>
      <div className="ui centered cards">
        {props.liveFeedData[0] !== undefined
          ? props.liveFeedData[0].map((eachData, index) => {
              let cardColor = "#FFE4E1";
              return (
                <div className="ui card" key={eachData.id}>
                  <div className="icon">
                    <h1
                      style={{
                        fontSize: "100px",
                        textAlign: "center",
                        background: cardColor,
                      }}
                    >
                      {smileys[eachData.rating - 1]}
                    </h1>
                  </div>
                  <div className="content">
                    <div className="header">{eachData.name}</div>
                    <div className="meta">
                      <span className="email">{eachData.email}</span>
                      <br />
                      <span className="date">
                        {eachData.dateTime.slice(0, 10)}
                      </span>
                    </div>
                    <div className="description">
                      {eachData.moodJustification}
                    </div>
                  </div>
                  <div className="extra content">
                    <Grid>
                      <Grid.Column width={2} style={{ float: "right" }}>
                        <i className="star yellow icon">
                          <h4 style={{ display: "inline" }}>
                            {eachData.rating}
                          </h4>
                        </i>
                      </Grid.Column>
                      <Grid.Column
                        width={14}
                        style={{ float: "right" }}
                        className="right aligned"
                      >
                        <i className="hashtag icon"></i>
                        <h4 style={{ display: "inline" }}>
                          {eachData.hashtag}
                        </h4>
                      </Grid.Column>
                    </Grid>
                  </div>
                </div>
              );
            })
          : null}
      </div>
    </div>
  );
};
const mapStateToProps = (state) => {
  return { liveFeedData: state.liveFeedData };
};
export default connect(mapStateToProps, { fetchLiveFeed })(LiveFeed);
