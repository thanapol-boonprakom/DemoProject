
import { Component } from "react";
import React from "react";
import Header from "../header";
import FormInput from "./formInputEnroll";

class StudentEnroll extends Component{
    render(){
        return(
            <div>
                <Header></Header>
                <FormInput></FormInput>
            </div>
        );
    }
}
export default StudentEnroll;