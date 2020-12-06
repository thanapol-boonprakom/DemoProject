import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";

export default function AddEnroll() {
  const [student_id, setStudentId] = useState("");
  const [subject_id, setSubjectId] = useState("");
  const [student, setStudent] = useState([]);
  const [subject, setSubject] = useState([]);
  const [detail, setDetail] = useState("");

  const [open, setOpen] = React.useState(false);
  const handleClickOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [openFalse, setOpenFalse] = React.useState(false);
  const handleClickOpenFalse = () => setOpenFalse(true);
  const handleCloseFalse = () => setOpenFalse(false);

  function validateForm() {
    return student_id.length > 0 && subject_id.length > 0;
  }

  const getStudentData = async () => {
    const res = await axios.get("/demoProject/allStudent");
    console.log(res.data);
    setStudent(res.data);
  };
  const getSubjectData = async () => {
    const res = await axios.get("/demoProject/allSubject");
    console.log(res.data);
    setSubject(res.data);
  };

  useEffect(() => {
    getStudentData();
    getSubjectData();
  }, []);

  const sendDataStudent = async (state) => {
    try {
      const res = await axios.post("/demoProject/addCourse", state);
      if (res.data == "Saved") {
        handleClickOpen();
      } else if (res.data == "Cannot register no more than 8 subjects") {
        setDetail("ลงทะเบียนได้ไม่เกิน 8 วิชา");
        handleClickOpenFalse();
      } else if (res.data == "Cannot Save Duplicate time") {
        setDetail("ไม่สามารถลงทะเบียนเรียนที่เวลาตรงกันได้");
        handleClickOpenFalse();
      } else {
        setDetail("กรุณาตรวจสอบข้อมูลให้ถูกต้อง");
        handleClickOpenFalse();
      }
      console.log(res.data);
    } catch (error) {}
  };

  function handleSubmit(event) {
    let state = {
      student_id: student_id,
      subject_id: subject_id,
    };
    sendDataStudent(state);
    console.log(JSON.stringify(state));
    event.preventDefault();
  }

  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Label>
          {" "}
          <h1>ลงทะเบียนเรียน</h1>
        </Form.Label>
        <Form.Group size="lg" controlId="branch">
          <Form.Label>นักเรียน</Form.Label>
          <Form.Control
            as="select"
            custom
            type="text"
            value={student_id}
            onChange={(e) => setStudentId(e.target.value)}
          >
            <option>กรุณาเลือกนักเรียน</option>
            {student.map((item, i) => {
              return (
                <option key={i} value={item.id}>
                  {item.first_name}&nbsp;&nbsp;{item.last_name}
                </option>
              );
            })}
          </Form.Control>
        </Form.Group>
        <Form.Group size="lg" controlId="branch">
          <Form.Label>รหัสวิชาเรียน</Form.Label>
          <Form.Control
            as="select"
            custom
            type="text"
            value={subject_id}
            onChange={(e) => setSubjectId(e.target.value)}
          >
            <option>กรุณาเลือกวิชาเรียน</option>
            {subject.map((item, i) => {
              return (
                <option key={i} value={item.id}>
                  {item.subject_id}&nbsp;&nbsp;{item.subject_name}
                </option>
              );
            })}
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
        <DialogTitle id="alert-dialog-title">{"ลงทะเบียนสำเร็จ"}</DialogTitle>
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
          {"ลงทะเบียนไม่สำเร็จ"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {detail}
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
