import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import { Redirect } from "react-router-dom";
import Modal from "react-bootstrap/Modal";

export default function AddSubjectForm() {
  const [subject_id, setSubjectId] = useState("");
  const [subject_credit, setSubjectCredit] = useState("");
  const [subject_name, setSubjectName] = useState("");
  const [subject_detail, setSubjectDetail] = useState("");
  const [start_date, setStartDate] = useState("");
  const [end_date, setEndDate] = useState("");
  const [day, setDay] = useState("");
  const [teacher_id, setTeacherId] = useState("");
  const [teacher, setTeacher] = useState([]);

  const [open, setOpen] = React.useState(false);
  const handleClickOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [openFalse, setOpenFalse] = React.useState(false);
  const handleClickOpenFalse = () => setOpenFalse(true);
  const handleCloseFalse = () => setOpenFalse(false);


  const getTeacherData = async () => {
    const res = await axios.get("/demoProject/allTeacher");
    console.log(res.data);
    setTeacher(res.data);
  };

  useEffect(() => {
    getTeacherData();
  }, []);

  function validateForm() {
    return (
      subject_credit.length > 0 &&
      subject_name.length > 0 &&
      start_date.length > 0 &&
      end_date.length > 0 &&
      day.length > 0 &&
      teacher_id.length > 0
    );
  }

  const sendDataTeacher = async () => {
    let state = {
      subject_id: subject_id,
      subject_credit: subject_credit,
      subject_name: subject_name,
      subject_detail: subject_detail,
      start_date: start_date + ":00.00",
      end_date: end_date + ":00.00",
      day: day,
      teacher_id: teacher_id,
    };
    console.log(JSON.stringify(state));
    try {
      const res = await axios.post("/demoProject/addSubject", state);
      console.log(res.data);
      if (res.data == "Saved") {
        handleClickOpen();
      } else {
        handleClickOpenFalse();
      }
    } catch (error) {}
  };

  function handleSubmit(event) {
    sendDataTeacher();
    event.preventDefault();
  }

  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Label>
          <h1>เพิ่มข้อมูลวิชา</h1>
        </Form.Label>
        <Form.Group size="lg" controlId="fname">
          <Form.Label>รหัสวิชา</Form.Label>
          <Form.Control
            autoFocus
            type="number"
            value={subject_id}
            onChange={(e) => setSubjectId(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="lname">
          <Form.Label>ชื่อวิชา</Form.Label>
          <Form.Control
            type="text"
            value={subject_name}
            onChange={(e) => setSubjectName(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="branch">
          <Form.Label>หน่วยกิต</Form.Label>
          <Form.Control
            type="number"
            step="0.1"
            value={subject_credit}
            onChange={(e) => setSubjectCredit(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="fname">
          <Form.Label>รายละเอียด</Form.Label>
          <Form.Control
            type="text"
            value={subject_detail}
            onChange={(e) => setSubjectDetail(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="lname">
          <Form.Label>เริ่มสอนเวลา</Form.Label>
          <Form.Control
            type="time"
            value={start_date}
            onChange={(e) => setStartDate(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="branch">
          <Form.Label>หมดเวลา</Form.Label>
          <Form.Control
            type="time"
            value={end_date}
            onChange={(e) => setEndDate(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="lname">
          <Form.Label>วัน</Form.Label>
          <Form.Control
            as="select"
            custom
            type="text"
            value={day}
            onChange={(e) => setDay(e.target.value)}
          >
            <option>กรุณาเลือกวัน</option>
            <option value="จันทร์">จันทร์</option>
            <option value="อังคาร">อังคาร</option>
            <option value="พุธ">พุธ</option>
            <option value="พฤหัสบดี">พฤหัสบดี</option>
            <option value="ศุกร์">ศุกร์</option>
          </Form.Control>
        </Form.Group>
        <Form.Group size="lg" controlId="branch">
          <Form.Label>คุณครูที่สอน</Form.Label>
          <Form.Control
            as="select"
            custom
            type="text"
            value={teacher_id}
            onChange={(e) => setTeacherId(e.target.value)}
          >
            <option>กรุณาเลือกครูผู้สอน</option>
            {teacher.map((item, i) => {
              return (
                <option key={i} value={item.id}>
                  {item.first_name}&nbsp;&nbsp;{item.last_name}
                </option>
              );
            })}
          </Form.Control>
        </Form.Group>
        <Button block size="lg" type="submit" disabled={!validateForm()}>
          เพิ่มข้อมูล
        </Button>
        <Dialog
          open={open}
          onClose={handleClose}
          aria-labelledby="alert-dialog-title"
          aria-describedby="alert-dialog-description"
        >
          <DialogTitle id="alert-dialog-title">
            {"เพิ่มข้อมูลสำเร็จ"}
          </DialogTitle>
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
      </Form>
    </div>
  );
}
