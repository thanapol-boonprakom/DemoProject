import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import React, { useState, useEffect, Component } from "react";
import axios from "axios";
import ReactTable from "react-table";
import "react-table/react-table.css";

export default class DataClassTable extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      loading: true,
      height: props.height,
    };

  }

  async getUsersData() {
    const res = await axios.get("/demoProject/allTeacher");
    console.log(res.data);
    this.setState({ loading: false, users: res.data });
  }
  componentWillMount() {
    this.setState({ height: window.innerHeight + "px" });
    
  }
  componentDidMount() {
    this.getUsersData();
  }

  render() {
    const columns = [
      {
        Header: "ID",
        accessor: "id",
      },
      {
        Header: "ชื่อ",
        accessor: "first_name",
      },

      {
        Header: "นามสกุล",
        accessor: "last_name",
      },
      {
        Header: "สาขา",
        accessor: "branch",
      },
    ];
    return (
      <div>
        <Paper>
          <TableContainer>
            <Table stickyHeader aria-label="sticky table">
              <TableHead>
                <TableRow>
                  {columns.map((column) => (
                    <TableCell align="center" style={{ minWidth: 150 }}>
                      {column.Header}
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {this.state.users.map((row) => (
                  <TableRow>
                    <TableCell align="center">{row.id}</TableCell>
                    <TableCell align="center">{row.first_name}</TableCell>
                    <TableCell align="center">{row.last_name}</TableCell>
                    <TableCell align="center">{row.branch}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            component="div"
            count={this.state.users.length}
            rowsPerPage={50}
            page={1}
            onChangePage={10}
            onChangeRowsPerPage={10}
          />
        </Paper>
        {/* <CustomizedTables></CustomizedTables>
        <StickyHeadTable></StickyHeadTable> */}
      </div>
    );
  }
}
