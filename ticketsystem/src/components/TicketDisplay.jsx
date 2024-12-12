// import PropTypes from 'prop-types';
//
// function TicketDisplay({ availableTickets, maxCapacity }) {
//     return (
//         <div className="ticket-display">
//             <h3>Ticket Availability</h3>
//             <p>
//                 <strong>Available Tickets:</strong> {availableTickets}
//             </p>
//             <p>
//                 <strong>Maximum Capacity:</strong> {maxCapacity}
//             </p>
//         </div>
//     );
// }
//
// TicketDisplay.propTypes = {
//     availableTickets: PropTypes.number.isRequired,
//     maxCapacity: PropTypes.number.isRequired,
// };
//
// export default TicketDisplay;

// **************************************************
// I used the above code for connect with the backend
// **************************************************


import PropTypes from 'prop-types';

function TicketDisplay({ availableTickets, maxCapacity }) {
    return (
        <div className="ticket-display">
            <h3>Ticket Availability</h3>
            <p>
                <strong>Available Tickets:</strong> {availableTickets}
            </p>
            <p>
                <strong>Maximum Capacity:</strong> {maxCapacity}
            </p>
        </div>
    );
}

TicketDisplay.propTypes = {
    availableTickets: PropTypes.number.isRequired,
    maxCapacity: PropTypes.number.isRequired,
};

export default TicketDisplay;