import React, { useState, useEffect } from 'react';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Title from './Title';

function preventDefault(event) {
  event.preventDefault();
}

async function getTotals() {
  const data = await fetch('/payments/total');
  return await data.json();
}

async function getStartDate() {
  const data = await fetch('/payments/start');
  return await data.json();
}

export default function Totals() {
  const [total, setTotal] = useState("");
  const [startDate, setStartDate] = useState("");
  useEffect(() => {
    let mounted = true;
    getTotals()
      .then(totals => {
        if(mounted) {
          setTotal(totals)
        }
      })
      getStartDate()
      .then(startDates => {
        if(mounted) {
          setStartDate(startDates)
        } 
      })
    return () => mounted = false;
  }, [])
  return (
    <React.Fragment>
      <Title>Total Earnings</Title>
      <Typography component="p" variant="h4">
        {total} €
      </Typography>
      <Typography color="text.secondary" sx={{ flex: 1 }}>
        Starting from  {startDate.start}
      </Typography>
      <div>
        <Link color="primary" href="#" onClick={preventDefault}>
          View Details
        </Link>
      </div>
    </React.Fragment>
  );
}