import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Card, Icon } from "semantic-ui-react";
import { fetchLiveFeed } from "../actions";

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

// const displayLiveFeed = (data) => {
//   // console.log(data[0]);
//   if (data[0] !== undefined) {
//     return data[0].forEach((eachData) => {
//       return (
//         <Card style={{ display: "inline" }} key={eachData.id}>
//           <Card.Content header={eachData.name} style={{color: "red"}} />
//           <Card.Content description={eachData.moodJustification} />
//           <Card.Content extra>
//             <Icon name="user" />
//             {eachData.rating}
//           </Card.Content>
//         </Card>
//       );
//     });
//   } else {
//     return null;
//   }
// };

const LiveFeed = (props) => {
  useEffect(() => {
    props.fetchLiveFeed();
  }, []);
  return (
    <div>
      <h1 style={{ paddingLeft: "15px" }}>Live Feed</h1>
      {props.liveFeedData[0] !== undefined
        ? props.liveFeedData[0].map((eachData) => {
            let cardColor = getColor();
            return (
              <Card
                color={cardColor}
                style={{ display: "inline-block", margin: "1%" }}
                key={eachData.id}
              >
                <Card.Content color={cardColor} header={eachData.name} />
                <Card.Content
                  color={cardColor}
                  description={eachData.moodJustification}
                />
                <Card.Content color={cardColor} extra>
                  <Icon name="user" />
                  {eachData.rating}
                </Card.Content>
              </Card>
            );
          })
        : null}
    </div>
  );
};
const mapStateToProps = (state) => {
  return { liveFeedData: state.liveFeedData };
};
export default connect(mapStateToProps, { fetchLiveFeed })(LiveFeed);
