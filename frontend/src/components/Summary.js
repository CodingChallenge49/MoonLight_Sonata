import axios from "axios";
import React from "react";
import Chart from "react-google-charts";
import { Icon, Label, Menu } from "semantic-ui-react";
import CalenderComponent from "./CalenderComponent";

const colors = [
  "red",
  "orange",
  "yellow",
  "olive",
  "green",
  "teal",
  "blue",
  "violet",
  "purple",
  "pink",
  "brown",
];

function getColor() {
  return colors[Math.floor(Math.random() * colors.length)];
}

const pieOptions = {
  title: "Mood Distribution",
  slices: [
    {
      color: "#2BB673",
    },
    {
      color: "#d91e48",
    },
    {
      color: "#007fad",
    },
    {
      color: "#e9a227",
    },
  ],
  legend: {
    position: "right",
    alignment: "center",
    textStyle: {
      color: "233238",
      fontSize: 24,
    },
  },
  tooltip: {
    showColorCode: true,
  },
  chartArea: {
    left: 0,
    top: 0,
    width: "100%",
    height: "80%",
  },
  fontName: "Roboto",
  is3D: true,
};
class Summary extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      columns: [[]],
      hastag: [],
    };
  }
  async dataLoader() {
    var today = new Date();
    var date =
      today.getFullYear() +
      "-" +
      (today.getMonth() + 1) +
      "-" +
      today.getDate();
    //For PieChart
    const result = await axios.get(
      `https://grads-coding-challenge-group-4.uc.r.appspot.com/getCountByRatingGroup/${date}`
    );
    let charData = [["call", "no"]];
    for (let column of result.data) {
      charData.push([column.rating, column.numPeople]);
    }

    //For HashTags
    const getHashtags = await axios.get(
      "https://grads-coding-challenge-group-4.uc.r.appspot.com/getCountByHashtag"
    );
    this.setState({ hashtag: getHashtags.data, columns: charData });
  }
  componentDidMount() {
    this.dataLoader();
  }
  state = {
    chartImageURI: "",
  };
  render() {
    console.log(this.state.hashtag);
    return (
      <div className="App">
        <div>
          <h1>Mood Summary of the Day!!</h1>
          <Chart
            chartType="PieChart"
            data={this.state.columns}
            options={pieOptions}
            graph_id="PieChart"
            width={"100%"}
            height={"400px"}
            legend_toggle
          />
        </div>
        <div>
          <h1>Trending Hashtags</h1>
          <Menu compact>
            {this.state.hashtag !== undefined ? (
              this.state.hashtag.map((eachHashtag, index) => {
                return (
                  <Menu.Item as="a" size="big" key={index}>
                    <Icon name="hashtag" /> {eachHashtag.hashtag}
                    <Label color={getColor()} floating>
                      {eachHashtag.count}
                    </Label>
                  </Menu.Item>
                );
              })
            ) : (
              <div></div>
            )}
          </Menu>
        </div>

        <div style={{ marginTop: "3%" }}>
          <h1>Mood Calendar</h1>
          <CalenderComponent />
          <div style={{ marginTop: "2%" }}>
            <svg
              style={{
                width: "15px",
                height: "15px",
                marginRight: "5px",
                borderRadius: "40%",
                marginLeft: "5px",
              }}
            >
              <rect
                style={{
                  width: "15px",
                  height: "15px",
                  fill: "red",
                  strokeWidth: "3",
                  stroke: "rgb(0,0,0)",
                  borderRadius: "20%",
                }}
              />
            </svg>
            Low Mood
            <svg
              style={{
                width: "15px",
                height: "15px",
                marginRight: "5px",
                borderRadius: "40%",
                marginLeft: "5px",
              }}
            >
              <rect
                style={{
                  width: "15px",
                  height: "15px",
                  fill: "yellow",
                  strokeWidth: "3",
                  stroke: "rgb(0,0,0)",
                  borderRadius: "20%",
                  marginRight: "5px",
                }}
              />
            </svg>
            Moderate Mood
            <svg
              style={{
                width: "15px",
                height: "15px",
                marginRight: "5px",
                borderRadius: "40%",
                marginLeft: "5px",
              }}
            >
              <rect
                style={{
                  width: "15px",
                  height: "15px",
                  fill: "green",
                  strokeWidth: "3",
                  stroke: "rgb(0,0,0)",
                  borderRadius: "20%",
                  marginRight: "5px",
                }}
              />
            </svg>
            Great Mood
            <svg
              style={{
                width: "15px",
                height: "15px",
                marginRight: "5px",
                borderRadius: "40%",
                marginLeft: "5px",
              }}
            >
              <rect
                style={{
                  width: "15px",
                  height: "15px",
                  fill: "blue",
                  strokeWidth: "3",
                  stroke: "rgb(0,0,0)",
                  borderRadius: "20%",
                  marginRight: "5px",
                }}
              />
            </svg>
            Today
          </div>
        </div>
      </div>
    );
  }
}

export default Summary;
