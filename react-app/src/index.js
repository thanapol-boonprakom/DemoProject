import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { Router, Route, Link, browserHistory } from "react-router";
import Header from "./components/header";
import addTeacher from "./components/teacherComponent/addTeacher";
import addSubject from "./components/subjectComponent/addSubject";
import addStudent from "./components/studentComponent/addStudent";
import showDataStudent from "./components/studentComponent/showDataStudent";
import showDataTeacher from "./components/teacherComponent/showDataTeacher";
import showDataSubject from "./components/subjectComponent/showDataSubject";
import studentEnroll from "./components/enrollComponent/studentEnroll";

/* The following line can be included in your src/index.js or App.js file */



ReactDOM.render(
  <Router history={browserHistory}>
    <Route path="/" component={App} />
    <Route path="/header" component={Header} />
    <Route path="/addTeacher" component={addTeacher} />
    <Route path="/addStudent" component={addStudent} />
    <Route path="/addSubject" component={addSubject} />
    <Route path="/showDataTeacher" component={showDataTeacher} />
    <Route path="/showDataStudent" component={showDataStudent} />
    <Route path="/showDataSubject" component={showDataSubject} />
    <Route path="/enroll" component={studentEnroll} />
  </Router>,document.getElementById('root')
);
