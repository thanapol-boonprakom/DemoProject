import "./App.css";
import { Component } from "react";
import React, { useState } from "react";
import Header from "./components/header";

class App extends Component {
  constructor() {
    super();
  }
  render() {
    return (
      <div>
        <Header></Header>
        <br></br>
        <center>
          <h1>ระบบลงทะเบียนเรียน</h1>
          <h3>
            กดปุ่มนี้<a href="/enroll">&nbsp;ลงทะเบียนเรียน</a>
          </h3>
        </center>
      </div>
    );
  }
}
export default App;

