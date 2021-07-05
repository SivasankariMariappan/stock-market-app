import ReactTable from "react-table"
import "react-table/react-table.css"
import React from "react"
import { connect } from "react-redux"
import moment from "moment"
import styled from "styled-components"
import Typography from "@material-ui/core/Typography"

const FlexRow = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
`
export class TableComponent extends React.Component {
  static defaultProps = {
    searchResults: {},
  }
  convertToLocalTimeStamp = dateString => {
    return moment(dateString).format("DD/MM/YYYY HH:mm")
  }

  render() {
    const {
      searchResults: { stockDetails, minPrice, maxPrice, avgPrice },
    } = this.props

    const columns = [
      {
        id: "stockPrice",
        Header: "StockPrice",
        accessor: "stockPrice",
        className: "stockPrice",
        headerClassName: "stockPrice",
        sortable: false,
      },
      {
        id: "stockDate",
        Header: "StockDate",
        accessor: "stockDate",
        className: "stockDate",
        headerClassName: "stockDate",
        sortable: false,
        Cell: props => this.convertToLocalTimeStamp(props.value),
      },
    ]
    return (
      <>
        <ReactTable
          data={stockDetails}
          columns={columns}
          showPaginationBottom={true}
          minRows={10}
        />
        <FlexRow>
          <Typography variant="body1">Minimum Stock Price</Typography>
          <Typography variant="body1">{minPrice}</Typography>
        </FlexRow>
        <FlexRow>
          <Typography variant="body1">Maximum Stock Price</Typography>
          <Typography variant="body1">{maxPrice}</Typography>
        </FlexRow>
        <FlexRow>
          <Typography variant="body1">Average Stock Price</Typography>
          <Typography variant="body1">{avgPrice}</Typography>
        </FlexRow>
      </>
    )
  }
}

const mapStateToProps = ({ company: { searchResults } }) => ({
  searchResults,
})

export default connect(mapStateToProps, null)(TableComponent)
