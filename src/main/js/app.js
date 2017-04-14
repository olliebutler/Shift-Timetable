const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {shifts: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/shifts'}).done(response => {
			this.setState({shifts: response.entity._embedded.shifts});
		});
	}

	render() {
		return (
			<ShiftList shifts={this.state.shifts}/>
		)
	}
}

class ShiftList extends React.Component{
	render() {
		var shifts = this.props.shifts.map(shift =>
			<Shift key={shift._links.self.href} shift={shift}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Date</th>
						<th>Shift Type</th>
					</tr>
					{shifts}
				</tbody>
			</table>
		)
	}
}

class Shift extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.shift.date}</td>
				<td>{this.props.shift.shiftType}</td>
			</tr>
		)
	}
}

ReactDOM.render(
		<App />,
		document.getElementById('react')
	)