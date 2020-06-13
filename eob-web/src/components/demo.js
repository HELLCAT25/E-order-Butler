import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Collapse from '@material-ui/core/Collapse';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import axios from "axios";
import authHeader from "../services/auth-header";


const useRowStyles = makeStyles({
    root: {
        '& > *': {
            borderBottom: 'unset',
        },
    },
});

function Row(props) {
    const { row } = props;
    const [open, setOpen] = React.useState(false);
    const classes = useRowStyles();

    return (
        <React.Fragment>
            <TableRow className={classes.root}>
                <TableCell>
                    <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>

                <TableCell align="right">{new Date(row.date).toISOString().slice(0,10)}</TableCell>
                <TableCell align="right">{row.merchant}</TableCell>
                <TableCell align="right">{row.orderNumber}</TableCell>
                <TableCell align="right">{!row.totalPrice ? "N/A" : row.totalPrice}</TableCell>
                <TableCell align="right">{row.status}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h6" gutterBottom component="div">
                                Items Details
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Item</TableCell>
                                        <TableCell>Status</TableCell>
                                        <TableCell align="right">Amount</TableCell>
                                        <TableCell align="right">Total Price ($)</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {row.items.map((historyRow) => (
                                        <TableRow key={historyRow.itemId}>
                                            <TableCell component="th" scope="row">
                                                {historyRow.itemName}
                                            </TableCell>
                                            <TableCell>{historyRow.status}</TableCell>
                                            <TableCell align="right">{historyRow.quantity}</TableCell>
                                            <TableCell align="right">
                                                {!historyRow.totalPrice ? "N/A" : historyRow.totalPrice}
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    );
}

Row.propTypes = {
    row: PropTypes.shape({
        calories: PropTypes.number.isRequired,
        carbs: PropTypes.number.isRequired,
        fat: PropTypes.number.isRequired,
        history: PropTypes.arrayOf(
            PropTypes.shape({
                amount: PropTypes.number.isRequired,
                customerId: PropTypes.string.isRequired,
                date: PropTypes.string.isRequired,
            }),
        ).isRequired,
        name: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        protein: PropTypes.number.isRequired,
    }).isRequired,
};


class Demo extends React.Component {

    render() {
        const PostData = this.props.PostData;

        return (
            <TableContainer component={Paper}>
                <Table aria-label="collapsible table">
                    <TableHead>
                        <TableRow>
                            <TableCell />
                            <TableCell align="right">Date</TableCell>
                            <TableCell align="right">Merchant</TableCell>
                            <TableCell align="right">Order Number</TableCell>
                            <TableCell align="right">Total Price ($)</TableCell>
                            <TableCell align="right">Status</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {PostData.map((row) => (
                            <Row key={row.name} row={row} />
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }
}

export default Demo;