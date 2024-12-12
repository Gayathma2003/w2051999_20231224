// import PropTypes from 'prop-types';
// import '../styles/LogDisplay.css'
//
//
// function LogDisplay({ logs }) {
//     return (
//         <div className="log_display">
//             <form className="Logging-form">
//             <h3>Logs</h3>
//             <ul>
//                 {logs.map((log, index) => (
//                     <li key={index}>{log}</li>
//                 ))}
//             </ul>
//             </form>
//         </div>
//     );
// }
//
// LogDisplay.propTypes = {
//     logs: PropTypes.func.isRequired,
// };
//
// export default LogDisplay;

// **************************************************
// I used the above code for connect with the backend
// **************************************************


import PropTypes from 'prop-types';
import '../styles/LogDisplay.css'


function LogDisplay({ logs }) {
    return (
        <div className="log_display">
            <form className="Logging-form">
                <h3>Logs</h3>
                <ul>
                    {logs.map((log, index) => (
                        <li key={index}>{log}</li>
                    ))}
                </ul>
            </form>
        </div>
    );
}

LogDisplay.propTypes = {
    logs: PropTypes.func.isRequired,
};

export default LogDisplay;
