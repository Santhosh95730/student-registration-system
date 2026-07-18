import { useEffect, useState } from 'react'
import axios from 'axios'

const API = 'http://localhost:8080/api'

function App() {
  const [events, setEvents] = useState([])
  const [selectedEvent, setSelectedEvent] = useState(null)
  const [participants, setParticipants] = useState([])
  const [message, setMessage] = useState('')

  const [form, setForm] = useState({
    studentName: '',
    email: '',
    department: '',
    year: ''
  })

  useEffect(() => {
    loadEvents()
  }, [])

  const loadEvents = async () => {
    const response = await axios.get(`${API}/events`)
    setEvents(response.data)
  }

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const register = async (e) => {
    e.preventDefault()

    if (!selectedEvent) {
      setMessage('Please select an event first.')
      return
    }

    try {
      await axios.post(`${API}/registrations`, {
        ...form,
        eventId: selectedEvent.id
      })

      setMessage('Registration successful!')
      setForm({
        studentName: '',
        email: '',
        department: '',
        year: ''
      })
    } catch (error) {
      setMessage('Registration failed. Please check your details.')
    }
  }

  const viewParticipants = async (event) => {
    setSelectedEvent(event)
    const response = await axios.get(`${API}/registrations/event/${event.id}`)
    setParticipants(response.data)
  }

  return (
    <div className="container">
      <header>
        <h1>College Event Registration</h1>
        <p>Students register for events and organizers view participants.</p>
      </header>

      <section className="grid">
        <div className="card">
          <h2>Available Events</h2>

          {events.map(event => (
            <div className="event" key={event.id}>
              <h3>{event.name}</h3>
              <p>{event.description}</p>
              <p><b>Date:</b> {event.eventDate}</p>
              <p><b>Venue:</b> {event.venue}</p>
              <button onClick={() => setSelectedEvent(event)}>
                Register
              </button>
              <button className="secondary" onClick={() => viewParticipants(event)}>
                View Participants
              </button>
            </div>
          ))}
        </div>

        <div className="card">
          <h2>Student Registration</h2>

          {selectedEvent && (
            <p className="selected">
              Selected Event: <b>{selectedEvent.name}</b>
            </p>
          )}

          <form onSubmit={register}>
            <input
              name="studentName"
              placeholder="Student Name"
              value={form.studentName}
              onChange={handleChange}
              required
            />

            <input
              name="email"
              type="email"
              placeholder="Email"
              value={form.email}
              onChange={handleChange}
              required
            />

            <input
              name="department"
              placeholder="Department"
              value={form.department}
              onChange={handleChange}
              required
            />

            <select name="year" value={form.year} onChange={handleChange} required>
              <option value="">Select Year</option>
              <option>1st Year</option>
              <option>2nd Year</option>
              <option>3rd Year</option>
              <option>4th Year</option>
            </select>

            <button type="submit">Submit Registration</button>
          </form>

          {message && <p className="message">{message}</p>}
        </div>
      </section>

      {selectedEvent && (
        <section className="card participants">
          <h2>Participants - {selectedEvent.name}</h2>

          {participants.length === 0 ? (
            <p>No participants registered yet.</p>
          ) : (
            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Department</th>
                  <th>Year</th>
                </tr>
              </thead>
              <tbody>
                {participants.map(participant => (
                  <tr key={participant.id}>
                    <td>{participant.studentName}</td>
                    <td>{participant.email}</td>
                    <td>{participant.department}</td>
                    <td>{participant.year}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </section>
      )}
    </div>
  )
}

export default App
