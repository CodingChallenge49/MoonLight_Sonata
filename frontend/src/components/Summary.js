import axios from "axios";
import React from "react";
import Chart from "react-google-charts";
import { Icon, Label, Menu } from "semantic-ui-react";

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
  title: "",
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
    date = "2021-08-15";
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
    );
  }
}

export default Summary;
