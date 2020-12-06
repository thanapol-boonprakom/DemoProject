import "./App.css";
import { Component } from "react";
import React, { useState } from "react";
import Header from "./components/header";
import axios from "axios";
import { Button } from "@material-ui/core";
import Modal from "react-bootstrap/Modal";

class App extends Component {
  constructor() {
    super();
    this.state = {
      data: [],
      messege: "UnLike",
      type: "",
      users: [],
    };
    this.changeMessege = this.changeMessege.bind(this);
    this.insertData = this.insertData.bind(this);
  }
  insertData() {
    var item = "React";
    var myArray = this.state.data;
    myArray.push(item);
    this.setState({ data: myArray });
  }
  changeMessege() {
    if (this.state.messege == "Like") {
      this.setState({ messege: "UnLike" });
    } else {
      this.setState({ messege: "Like" });
    }
  }

  onChange(event) {
    this.setState({ type: event.target.value });
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

