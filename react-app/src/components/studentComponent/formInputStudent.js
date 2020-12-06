import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";

export default function AddTeacherForm() {
  const [first_name, setFirstName] = useState("");
  const [last_name, setLastName] = useState("");
  const [year, setYear] = useState("");

  const [open, setOpen] = React.useState(false);
  const handleClickOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [openFalse, setOpenFalse] = React.useState(false);
  const handleClickOpenFalse = () => setOpenFalse(true);
  const handleCloseFalse = () => setOpenFalse(false);

  function validateForm() {
    return first_name.length > 0 && last_name.length > 0 && year.length > 0;
  }

  const sendDataStudent = async (state) => {
    try {
      const res = await axios.post("/demoProject/addStudent", state);
      console.log(res.data);
      if (res.data == "Saved") {
        handleClickOpen();
      } else {
        handleCloseFalse();
      }
    } catch (error) {}
  };

  function handleSubmit(event) {
    let state = {
      first_name: first_name,
      last_name: last_name,
      year: year,
    };
    sendDataStudent(state);
    // handleClickOpen();
    console.log(JSON.stringify(state));
    event.preventDefault();
  }

  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Label>
          {" "}
          <h1>เพิ่มข้อมูลนักเรียน</h1>
        </Form.Label>
        <Form.Group size="lg" controlId="fname">
          <Form.Label>ชื่อ</Form.Label>
          <Form.Control
            autoFocus
            type="text"
            value={first_name}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="lname">
          <Form.Label>นามสกุล</Form.Label>
          <Form.Control
            type="text"
            value={last_name}
            onChange={(e) => setLastName(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="branch">
          <Form.Label>ชั้นปี</Form.Label>
          <Form.Control
            as="select"
            custom
            type="text"
            value={year}
            onChange={(e) => setYear(e.target.value)}
          >
            <option>กรุณาเลือกชั้นปี</option>
            <option value="ปี1">ปี1</option>
            <option value="ปี2">ปี2</option>
            <option value="ปี3">ปี3</option>
            <option value="ปี4">ปี4</option>
            <option value="ปี5">ปี5</option>
            <option value="ปี6">ปี6</option>
          </Form.Control>
        </Form.Group>
        <Button block size="lg" type="submit" disabled={!validateForm()}>
          เพิ่มข้อมูล
        </Button>
      </Form>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"เพิ่มข้อมูลสำเร็จ"}</DialogTitle>
        <DialogActions>
          <Button onClick={handleClose} color="primary" autoFocus>
            ยืนยัน
          </Button>
        </DialogActions>
      </Dialog>
      <Dialog
        open={openFalse}
        onClose={handleCloseFalse}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"เพิ่มข้อมูลไม่สำเร็จ"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            กรุณาตรวจสอบข้อมูลให้ถูกต้อง
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseFalse} color="primary" autoFocus>
            ยืนยัน
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
