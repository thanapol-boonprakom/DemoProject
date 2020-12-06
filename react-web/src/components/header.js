import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import fileSaver from 'file-saver';
import "../styles/stylesDropdown.css";
import NavDropdown from "react-bootstrap/NavDropdown";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import "bootstrap/dist/css/bootstrap.css";
import axios from "axios";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));
const navbar = { backgroundColor: "#1976D2" };
const textStyle = { color: "white" };
export default function ButtonAppBar() {
  const classes = useStyles();

  const getExcelData = async () => {
    const res = await axios
      .get("/demoProject/exportToExcel", { responseType: "arraybuffer" })
      .then((response) => {
        var blob = new Blob([response.data], {
          type:
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        });
        fileSaver.saveAs(blob, "ช้อมูลการลงทะเบียน.xlsx");
      });
  };

  return (
    <div className={classes.root}>
      <Navbar collapseOnSelect expand="lg" style={navbar}>
        <Navbar.Brand href="/" style={textStyle}>
          ระบบลงทะเบียนเรียน
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto"></Nav>
          <Nav>
            <NavDropdown style={textStyle} title="คุณครู">
              <NavDropdown.Item href="/showDataTeacher">
                แสดงข้อมูลคุณครู
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="/addTeacher">
                เพิ่มข้อมูลคุณครู
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="นักเรียน" id="collasible-nav-dropdown">
              <NavDropdown.Item href="/showDataStudent">
                แสดงข้อมูลนักเรียน
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="/addStudent">
                เพิ่มข้อมูลนักเรียน
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="วิชาเรียน" id="collasible-nav-dropdown">
              <NavDropdown.Item href="/showDataSubject">
                แสดงข้อมูลวิชาเรียน
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="/addSubject">
                เพิ่มข้อมูลวิชาเรียน
              </NavDropdown.Item>
            </NavDropdown>
            <Nav.Item>
              <Nav.Link onClick={getExcelData}>
                ข้อมูลการลงทะเบียนเรียน
              </Nav.Link>
            </Nav.Item>
            <Button
              style={textStyle}
              variant="outlined"
              color="inherit"
              href="/enroll"
            >
              ลงทะเบียน
            </Button>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
}
