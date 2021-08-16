import _ from "lodash";
import faker from "faker";
import React, { useEffect, useRef, useState } from "react";
import { Search, Grid } from "semantic-ui-react";
import { clean, data, finishSearch, updateSelect } from "../actions";
import { connect } from "react-redux";
import axios from "axios";

function Hashtag(props) {
  const timeoutRef = useRef();
  const [source, setSource] = useState([]);
  const sourceRef = useRef();
  sourceRef.current = source;

  async function fetchData() {
    const result = await axios.get("https://grads-coding-challenge-group-4.uc.r.appspot.com/getAllHashtags");
    setSource(result.data);
  }
  useEffect(() => {
    fetchData();
    return () => {
      clearTimeout(timeoutRef.current);
    };
  }, []);
  const handleSearchChange = React.useCallback((e, data) => {
    clearTimeout(timeoutRef.current);
    props.data(data);

    timeoutRef.current = setTimeout(() => {
      if (data.value.length === 0) {
        props.clean();
        return;
      }

      const re = new RegExp(_.escapeRegExp(data.value), "i");
      const isMatch = (result) => re.test(result.title);
      props.finishSearch(sourceRef.current, isMatch);
    }, 300);
  }, []);

  return (
    <Grid>
      <Grid.Column width={6}>
        <Search
          loading={props.exampleReducer.loading}
          onResultSelect={(e, data) => {
            props.updateSelect(data);
          }}
          onSearchChange={handleSearchChange}
          results={props.exampleReducer.results}
          value={props.exampleReducer.value}
        />
      </Grid.Column>
    </Grid>
  );
}

const mapStateToProps = (state) => {
  return { exampleReducer: state.exampleReducer };
};

export default connect(mapStateToProps, {
  data: data,
  clean: clean,
  finishSearch: finishSearch,
  updateSelect: updateSelect,
})(Hashtag);
