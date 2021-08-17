import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

function CalenderComponent(props) {
  const [value, setValue] = useState(new Date());
  const [dbDate, setDbDate] = useState([]);
  const dbDateRef = useRef();
  dbDateRef.current = dbDate;

  function tileClassName({ date, view }) {
    var date1 =
      date.getFullYear() +
      "-" +
      ("0" + (date.getMonth() + 1)).slice(-2) +
      "-" +
      ("0" + date.getDate()).slice(-2);

    if (view === "month") {
      const index = dbDateRef.current.findIndex(
        (dDate) => Date.parse(dDate.date.toString()) === Date.parse(date1)
      );
      if (index != -1) {
        if (dbDateRef.current[index].rating === "red") {
          return "redColor";
        } else if (dbDateRef.current[index].rating === "yellow") {
          return "yellowColor";
        } else {
          return "greenColor";
        }
      }
    }
    return "tileColor";
  }
  async function fetchData() {
    const result = await axios.get(
      "https://grads-coding-challenge-group-4.uc.r.appspot.com/getRatingForCalendar"
    );
    setDbDate(result.data);
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div style={{ marginLeft: "35%" }}>
      <Calendar
        onChange={setValue}
        value={value}
        tileClassName={tileClassName}
      />
    </div>
  );
}

export default CalenderComponent;
