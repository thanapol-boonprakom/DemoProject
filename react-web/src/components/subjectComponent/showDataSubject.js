import React, {  Component } from "react";
import axios from "axios";
import Header from "../header";
import { DataGrid } from "@material-ui/data-grid";
const columns = [
  { field: "id", headerName: "ID", width: 50 },
  { field: "subject_id", headerName: "รหัสวิชา", width: 80 },
  { field: "subject_name", headerName: "ชื่อวิชา", width: 150 },
  { field: "subject_credit", headerName: "หน่วยกิต", width: 100 },
  { field: "subject_detail", headerName: "รายละเอียด", width: 130 },
  { field: "start_date", headerName: "เริ่มเวลา", width: 130 },
  { field: "end_date", headerName: "หมดเวลา", width: 130 },
  { field: "day", headerName: "วัน", width: 80 },
  { field: "teacher", headerName: "อาจารย์ที่สอน", width: 130 },
];
export default class ShowDataSubject extends Component {
  constructor(props) {
    super(props);
    this.state = {
      subject: [],
      loading: true,
      height: props.height,
    };
  }

  async getSubjectData() {
    const res = await axios.get("/demoProject/allSubject");
    console.log(res.data);
    this.setState({ loading: false, subject: res.data });
  }
  componentWillMount() {
    this.setState({ height: window.innerHeight + "px" });
  }
  componentDidMount() {
    this.getSubjectData();
  }

  render() {
    return (
      <div>
        <Header></Header>
        <div style={{ height: 650, width: "100%" }}>
          <DataGrid rows={this.state.subject} columns={columns} pageSize={10} />
        </div>
      </div>
    );
  }
}
