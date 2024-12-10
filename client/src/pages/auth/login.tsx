import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { sha256 } from 'js-sha256';
import classes from './login.module.css'
import { useData } from "../../hooks/useData";

export default function Login() {
    const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
    const [error, setError] = useState(false);
    const { loginUser } = useData();
    const navigate = useNavigate();

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const hashedPassword = sha256(password);
            const userData = {
                email,
                password: hashedPassword
            };

            const response = await loginUser(userData);
            if (response.success) {
                navigate('/');
            } else {
                setError(true);
                console.error('Login failed:', response.message);
            }
        } catch (error) {
            setError(true);
            console.error('Login error:', error);
        }
    }

    return (
        <form onSubmit={handleSubmit} className={classes.form} aria-label="Login form">
			<div className={classes.title}>Login</div>

            <div className={classes.userInput}>
                <label className={classes.label}>
                    <span>email:</span>
                    <input
                        required
                        className={classes.input}
                        type="email"
                        onChange={(e) => {
                            setError(false);
                            setEmail(e.target.value)
                        }}
                        value={email}
                    />
                </label>
                <label className={classes.label}>
                    <span>password:</span>
                    <input
                        required
                        className={classes.input}
                        type="password"
                        onChange={(e) => {
                            setError(false);
                            setPassword(e.target.value);
                        }}
                        value={password}
                    />
                </label>
            </div>

			<button className={classes.login_button}>Log in</button>
            {error && <div className={classes.error}>Something went wrong. Please, try again.</div>}
		</form>
    )
}