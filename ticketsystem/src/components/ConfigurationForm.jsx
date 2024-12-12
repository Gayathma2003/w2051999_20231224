// import { useState } from 'react';
// import PropTypes from 'prop-types';
// import {submitConfiguration} from "../services/api.js";
// import '../styles/Configuration.css'
//
// function ConfigurationForm ({onSubmit}) {
//     const [vendorMaxTicketCapacity, setVendorMaxTicketCapacity] = useState('');
//     const [ticketReleaseRate, setTicketReleaseRate] = useState('');
//     const [customerRetrievalRate, setCustomerRetrievalRate] = useState('');
//     const [maxCapacity, setMaxTicketCapacity] = useState('');
//     const [ticketPerCustomer, setTicketPerCustomer] = useState('');
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         const configData = {
//             maxTicketCapacity: vendorMaxTicketCapacity,
//             releaseRateInSeconds: ticketReleaseRate,
//             retrieveRateInSeconds: customerRetrievalRate,
//             maxCapacity: maxCapacity,
//             ticketsPerCustomer: ticketPerCustomer,
//         };
//
//         try {
//             console.log('Sending Config Data:', configData);
//             await submitConfiguration(configData);
//             console.log('Configuration successful!');
//             onSubmit(configData);
//
//         } catch (error) {
//             console.error('Error during configuration:', error);
//         }
//     };
//
//     return (
//         <form className="configuration-form" onSubmit={handleSubmit}>
//             <h2>System Configuration</h2>
//             <div>
//                 <label>Amount of tickets each vendor can release:</label>
//                 <input type="number" value={vendorMaxTicketCapacity} min="1" onChange={(e) => setVendorMaxTicketCapacity(e.target.value)}
//                        required/>
//             </div>
//             <div>
//                 <label>Ticket Release Rate (in seconds):</label>
//                 <input type="number" value={ticketReleaseRate} min="1"
//                        onChange={(e) => setTicketReleaseRate(e.target.value)} required/>
//             </div>
//             <div>
//                 <label>Customer Retrieval Rate (in seconds):</label>
//                 <input type="number" value={customerRetrievalRate} min="1"
//                        onChange={(e) => setCustomerRetrievalRate(e.target.value)} required/>
//             </div>
//             <div>
//                 <label>Maximum Ticket Capacity in the Ticket Pool:</label>
//                 <input type="number" value={maxCapacity} min="1"
//                        onChange={(e) => setMaxTicketCapacity(e.target.value)} required/>
//             </div>
//             <div>
//                 <label>Amount of tickets each customer can purchase:</label>
//                 <input type="number" value={ticketPerCustomer} min="1" onChange={(e) => setTicketPerCustomer(e.target.value)}
//                        required/>
//             </div>
//             <button className="submit-button" type="submit">Submit Configuration</button>
//         </form>
//     );
// }
//
// ConfigurationForm.propTypes = {
//     onSubmit: PropTypes.func.isRequired,
// };
//
// export default ConfigurationForm;

// **************************************************
// I used the above code for connect with the backend
// **************************************************


import { useState } from 'react';
import PropTypes from 'prop-types';
import '../styles/Configuration.css'

function ConfigurationForm({ onSubmit }) {
    const [totalTickets, setTotalTickets] = useState('');
    const [ticketReleaseRate, setTicketReleaseRate] = useState('');
    const [customerRetrievalRate, setCustomerRetrievalRate] = useState('');
    const [maxTicketCapacity, setMaxTicketCapacity] = useState('');
    const [ticketPerCustomer, setTicketPerCustomer] = useState('');


    const handleSubmit = (e) => {
        e.preventDefault();

        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 || maxTicketCapacity <= 0) {
            alert('All values must be positive numbers greater than 0.');
            return;
        }

        onSubmit({
            totalTickets: parseInt(totalTickets),
            ticketReleaseRate: parseInt(ticketReleaseRate),
            customerRetrievalRate: parseInt(customerRetrievalRate),
            maxTicketCapacity: parseInt(maxTicketCapacity),
            ticketPerCustomer: parseInt(ticketPerCustomer),
        });
    };

    return (
        <form className="configuration-form" onSubmit={handleSubmit}>
            <h2>System Configuration</h2>
            <div>
                <label>Amount of tickets each vendor can release:</label>
                <input type="number" value={totalTickets} min="1" onChange={(e) => setTotalTickets(e.target.value)}
                       required/>
            </div>
            <div>
                <label>Ticket Release Rate (in seconds):</label>
                <input type="number" value={ticketReleaseRate} min="1"
                       onChange={(e) => setTicketReleaseRate(e.target.value)} required/>
            </div>
            <div>
                <label>Customer Retrieval Rate (in seconds):</label>
                <input type="number" value={customerRetrievalRate} min="1"
                       onChange={(e) => setCustomerRetrievalRate(e.target.value)} required/>
            </div>
            <div>
                <label>Maximum Ticket Capacity in the Ticket Pool:</label>
                <input type="number" value={maxTicketCapacity} min="1"
                       onChange={(e) => setMaxTicketCapacity(e.target.value)} required/>
            </div>
            <div>
                <label>Amount of tickets each customer can purchase:</label>
                <input type="number" value={ticketPerCustomer} min="1" onChange={(e) => setTicketPerCustomer(e.target.value)}
                       required/>
            </div>
            <button className="submit-button" type="submit">Submit Configuration</button>
        </form>
    );
}

ConfigurationForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default ConfigurationForm;