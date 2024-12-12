// import { useState } from 'react';
// import ConfigurationForm from './components/ConfigurationForm.jsx';
// import ControlPanel from './components/ControlPanel.jsx';
// import LogDisplay from './components/LogDisplay.jsx';
// import TicketDisplay from "./components/TicketDisplay.jsx";
// import './App.css';
// import {getAvailableTickets, startSystem, stopSystem} from "./services/api.js";
//
//
// function App() {
//     const [config, setConfig] = useState(null);
//     const [logs, setLogs] = useState([]);
//     const [isSystemRunning, setIsSystemRunning] = useState(false);
//     const [availableTickets, setAvailableTickets] = useState(0);
//
//     const handleConfigSubmit = (configData) => {
//         setConfig(configData);
//         logMessage('Configuration submitted!');
//         handleStartSystem();
//     };
//
//     const handleStartSystem = async () => {
//         if (config) {
//             try {
//                 await startSystem({
//                     vendorId: 'Vendor-1',
//                     maxTicketCapacity: config.maxTicketCapacity,
//                     releaseRateInSeconds: config.releaseRateInSeconds,
//                     customerId: 'Customer-1',
//                     maxTicketsPerCustomer: config.maxTicketsPerCustomer,
//                     retrieveRateInSeconds: config.retrieveRateInSeconds
//                 });
//                 setIsSystemRunning(true);
//                 logMessage('System started.');
//                 fetchAvailableTickets();
//             }catch (error){
//                 logMessage('Error starting system: ' +error.message);
//             }
//         } else {
//             logMessage('Please submit configuration first.');
//         }
//     };
//
//     const handleStopSystem = async () => {
//         try {
//             await stopSystem();
//             setIsSystemRunning(false);
//             logMessage('System stopped.');
//         }catch (error){
//             logMessage('Error stopping system: ' +error.message);
//         }
//     };
//
//     const logMessage = (message) => {
//         setLogs((prevLogs) => [...prevLogs, `${new Date().toLocaleTimeString()}: ${message}`]);
//     };
//
//     const fetchAvailableTickets = async () => {
//         try {
//             const data = await getAvailableTickets();
//             setAvailableTickets(data.availableTickets);
//         }catch (error) {
//             logMessage("Error fetching."+error.message);
//         }
//     };
//
//     return (
//         <div className="App">
//             {!config && <ConfigurationForm onSubmit={handleConfigSubmit} />}
//             {config && (
//                 <>
//                     <ControlPanel onStart={handleStartSystem} onStop={handleStopSystem} />
//                     <TicketDisplay availableTickets={logs.length > 0 ? logs[logs.length - 1] : 0} maxCapacity={config.maxTicketCapacity} />
//
//                     <LogDisplay logs={logs} />
//                     {isSystemRunning}
//                     {availableTickets}
//                 </>
//             )}
//         </div>
//     );
// }
//
// export default App;

// **************************************************
// I used the above code for connect with the backend
// **************************************************

import { useState } from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import ControlPanel from './components/ControlPanel';
import LogDisplay from './components/LogDisplay';
import './App.css';

function App() {
    const [config, setConfig] = useState(null);
    const [logs, setLogs] = useState([]);
    const [isSystemRunning, setIsSystemRunning] = useState(false);
    const [vendors, setVendors] = useState([]);
    const [customers, setCustomers] = useState([]);

    const handleConfigSubmit = (configData) => {
        setConfig(configData);
        logMessage('Configuration submitted!');
    };

    const handleStartSystem = () => {
        if (config) {
            setIsSystemRunning(true);
            logMessage('System started.');
        } else {
            logMessage('Please submit configuration first.');
        }
    };

    const handleStopSystem = () => {
        setIsSystemRunning(false);
        logMessage('System stopped.');
    };

    const logMessage = (message) => {
        setLogs((prevLogs) => [...prevLogs, `${new Date().toLocaleTimeString()}: ${message}`]);
    };

    const addVendor = () => {
        setVendors((prev) => [...prev, `Vendor ${prev.length + 1}`]);
        logMessage(`Vendor ${vendors.length + 1} added.`);
    };

    // Remove vendor
    const removeVendor = () => {
        if (vendors.length > 0) {
            setVendors((prev) => prev.slice(0, -1));
            logMessage(`Vendor ${vendors.length} removed.`);
        }
    };

    // Add customer
    const addCustomer = () => {
        setCustomers((prev) => [...prev, `Customer ${prev.length + 1}`]);
        logMessage(`Customer ${customers.length + 1} added.`);
    };

    // Remove customer
    const removeCustomer = () => {
        if (customers.length > 0) {
            setCustomers((prev) => prev.slice(0, -1));
            logMessage(`Customer ${customers.length} removed.`);
        }
    };

    return (
        <div className="App">
            {!config && <ConfigurationForm onSubmit={handleConfigSubmit} />}
            {config && (
                <>
                    <ControlPanel onStart={handleStartSystem} onStop={handleStopSystem} />
                    <div className="management-buttons">
                        <h3>Vendor Management</h3>
                        <button className="management-button" onClick={addVendor}>Add Vendor</button>
                        <button className="management-button" onClick={removeVendor}>Remove Vendor</button>
                        <p>Active Vendors: {vendors.length}</p>

                        <h3>Customer Management</h3>
                        <button className="management-button" onClick={addCustomer}>Add Customer</button>
                        <button className="management-button" onClick={removeCustomer}>Remove Customer</button>
                        <p>Active Customers: {customers.length}</p>
                    </div>
                    <LogDisplay logs={logs} />
                    {isSystemRunning}
                </>
            )}
        </div>
    );
}


export default App;
