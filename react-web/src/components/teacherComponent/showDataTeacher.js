import { Component } from "react";
import React from "react";
import axios from "axios";
import Header from "../header";
import { DataGrid } from "@material-ui/data-grid";

const columns = [
  { field: "id", headerName: "ID", width: 150 },
  { field: "first_name", headerName: "ชื่อ", width: 200 },
  { field: "last_name", headerName: "นามสกุล", width: 200 },
  {
    field: "branch",
    headerName: "สาขา",
    width: 200
  },
];

class ShowDataTeacher extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      loading: true,
      height: props.height,
    };
  }

  async getTeacherData() {
    const res = await axios.get("/demoProject/allTeacher");
    console.log(res.data);
    this.setState({ loading: false, users: res.data });
  }
  componentWillMount() {
    this.setState({ height: window.innerHeight + "px" });
  }
  componentDidMount() {
    this.getTeacherData();
  }

  render() {
    return (
      <div>
        <Header></Header>
        <div style={{ height: 650, width: "100%" }}>
          <DataGrid rows={this.state.users} columns={columns} pageSize={10} />
        </div>
      </div>
    );
  }
}
export default ShowDataTeacher;
