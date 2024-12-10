import React from 'react'
import { useState, useEffect } from 'react';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import CloseIcon from '@mui/icons-material/Close';


//styles
import classes from './chat.module.css';


export default function Navbar() {
    const [isChatOpen, setIsChatOpen] = useState(false);
    const [isMassageSent, setIsMessageSent] = useState(false);

    const toggleChat = () => {
        setIsChatOpen(!isChatOpen);
    }

    const handleMessage = () => {
        setIsMessageSent(!isMassageSent);
    }

    useEffect(() => {
        if (!isChatOpen) {
            setIsMessageSent(false);
        };
    }, [isChatOpen]);

    return (
        <>
            {isChatOpen
                ? <CloseIcon className={classes.helpIcon} onClick={toggleChat}/>
                : <HelpOutlineIcon className={classes.helpIcon} onClick={toggleChat}/>
            }

            {isChatOpen &&
                <div>
                    <div className={classes.chatWindow}>
                        <div className={classes.chatTitle}>Chat with us</div>
                        <div className={classes.chatContent}>
                            {!isMassageSent
                                ? (
                                    <>
                                        <div className={classes.chatText}>How can we help you?</div>
                                        <input type="text" placeholder="Type your message..." className={classes.input} />
                                        <button className={classes.sendButton} onClick={handleMessage}>Send</button>
                                    </>
                                ) : (
                                <>
                                    <div className={classes.chatText}>Thank you for your message!</div>
                                    <button className={classes.sendButton} onClick={handleMessage}>Send another message</button>
                                </>
                                )
                            }
                        </div>
                    </div>
              </div>
            }
        </>
    )
}
