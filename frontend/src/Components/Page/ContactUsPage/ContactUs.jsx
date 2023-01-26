import "./contactUs.css";
import React from "react";
import { Form } from "reactstrap";

export const ContactUs = () => {
    
    return (
        <div className="contact-form">
            <h1 className="contact-title">Contact Us</h1>
            <Form className="contact-us">
                <div className="form">
                    <div>
                        <label className="name-label" itemType="name">Name: </label>
                        <input className="name" itemType="text"/>
                    </div>
                    <div>
                        <label className="email-label" itemType="email">Email Address: </label>
                        <input className="email" itemType="email"/>
                    </div>
                    <div>
                        <label className="text-label" itemType="text">Your Questions: </label>
                        <input className="text" itemType="text" />
                    </div>
                    <div className="submit-div">
                        <button className="submit" itemType="submit" href="#">Submit</button>
                    </div>
                </div>
            </Form>
        </div>
    )
}