// import PropTypes from 'prop-types';
// import '../styles/ControlPanel.css'
//
// function ControlPanel({ onStart, onStop }) {
//     return (
//         <div className="control_panel">
//             <button className="start_btn" onClick={onStart}>Start System</button>
//             <button className="stop_btn" onClick={onStop}>Stop System</button>
//         </div>
//     );
// }
//
// ControlPanel.propTypes = {
//     onStart: PropTypes.func.isRequired,
//     onStop: PropTypes.func.isRequired,
// };
//
// export default ControlPanel;

// **************************************************
// I used the above code for connect with the backend
// **************************************************


import PropTypes from 'prop-types';
import '../styles/ControlPanel.css'

function ControlPanel({ onStart, onStop }) {
    return (
        <div className="control_panel">
            <button className="start_btn" onClick={onStart}>Start System</button>
            <button className="stop_btn" onClick={onStop}>Stop System</button>
        </div>
    );
}

ControlPanel.propTypes = {
    onStart: PropTypes.func.isRequired,
    onStop: PropTypes.func.isRequired,
};

export default ControlPanel;
