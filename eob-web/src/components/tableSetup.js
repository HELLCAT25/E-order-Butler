import React, { Component }  from 'react';

export const columns = [
    {
        Header: () => (
            <div />
        ),
        accessor: 'name',
        maxWidth: 300,
        Cell: row => (
            <div className='first-column'>{row.value}</div>
        )
    }
];

export const subComponent = row => {
    return (
        <div>
            {row.original.types.map((type, id) => {
                return (
                    <div className='subRow' key={ id }>{ type.name }</div>
                );
            })}
        </div>
    );
};

export const data = [
    {
        id: '12345',
        name: 'sports',
        types: [
            {
                name: 'basketball',
                id: '1'
            },
            {
                name: 'soccer',
                id: '2'

            },
            {
                name: 'baseball',
                id: '3'
            }
        ]
    },
    {
        id: '678910',
        name: 'food',
        types: [
            {
                name: 'pizza',
                id: '4'
            },
            {
                name: 'hamburger',
                id: '5'

            },
            {
                name: 'salad',
                id: '6'
            }
        ]
    }
];