//directly pass the value to be set
import axios from "axios";
import _ from "lodash";
export const toggleHamburger = (isOpen) => {
  return {
    type: "TOGGLE_SIDEBAR",
    payload: isOpen,
  };
};

export const isVisible = (selected) => {
  return {
    type: "SELECT_NAV_OPTION",
    payload: selected,
  };
};

export const fetchLiveFeed = () => async (dispatch) => {
  const data = await axios.get("https://grads-coding-challenge-group-4.uc.r.appspot.com/getTopHistory/50");
  console.log(data);
  dispatch({ type: "LIVE_FEED", payload: data.data });
};

export const data = (data) => {
  return { type: "START_SEARCH", query: data.value };
};

export const clean = () => {
  return { type: "CLEAN_QUERY" };
};

export const finishSearch = (source, isMatch) => {
  return {
    type: "FINISH_SEARCH",
    results: _.filter(source, isMatch),
  };
};

export const updateSelect = (data) => {
  return { type: "UPDATE_SELECTION", selection: data.result.title };
};
