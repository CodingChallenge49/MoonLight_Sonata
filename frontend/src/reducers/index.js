import { combineReducers } from "redux";

const initialState = {
  loading: false,
  results: [],
  value: "",
};
//first initialization should be provided
const showSidebar = (isOpen = false, action) => {
  if (action.type === "TOGGLE_SIDEBAR") return action.payload;
  return isOpen;
};

const selectedNav = (option = "live", action) => {
  if (action.type === "SELECT_NAV_OPTION") {
    return action.payload;
  }
  return option;
};

const liveFeed = (data = [], action) => {
  if (action.type === "LIVE_FEED") {
    return [action.payload];
  }
  return data;
};

function exampleReducer(state, action) {
  switch (action.type) {
    case "CLEAN_QUERY":
      return initialState;
    case "START_SEARCH":
      return { ...state, loading: true, value: action.query };
    case "FINISH_SEARCH":
      return { ...state, loading: false, results: action.results };
    case "UPDATE_SELECTION":
      return { ...state, value: action.selection };
  }
  return initialState;
}
const reducers = combineReducers({
  isOpen: showSidebar,
  selectedNavOption: selectedNav,
  liveFeedData: liveFeed,
  exampleReducer: exampleReducer,
});
export default reducers;
