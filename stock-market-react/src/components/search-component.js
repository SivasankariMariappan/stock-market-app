import React from "react"
import styled from "styled-components"
import { connect } from "react-redux"
import Typography from "@material-ui/core/Typography"
import MenuItem from "@material-ui/core/MenuItem"
import Select from "@material-ui/core/Select"
import DatePicker from "react-datepicker"
import Button from "@material-ui/core/Button"
import "react-datepicker/dist/react-datepicker.css"
import "react-datepicker/dist/react-datepicker-cssmodules.css"
import Grid from "@material-ui/core/Grid"
import {
  getAllCompanies,
  handleChange,
  onSearch,
} from "../actions/company-actions"

const StyledGrid = styled(Grid)`
  margin-right: 40px !important;
  border-right: solid 1px #dddddd;
  height: 100%;
  > * {
    padding: 10px;
  }
  & .react-datepicker__input-container {
    input {
      width: 100%;
      font: inherit;
      color: black;
      border: 0;
      margin: 0;
      padding: 6px 0 7px;
      display: block;
      min-width: 0;
      flex-grow: 1;
      box-sizing: content-box;
      background: none;
      -webkit-tap-highlight-color: transparent;
      &:placeholder {
        color: #9b9b9b;
      }
      &:focus {
        outline: none;
      }
    }
  }
  & .react-datepicker__time-list {
    padding: 0;
    width: auto;
    height: auto;
  }
  & .react-datepicker__header--time {
    padding-top: 2.5em !important;
  }
`
const DisplayText = styled(Typography)`
  font-weight: bold !important;
`

const StyledBtn = styled(Button)`
  && {
    background-color: #0064d2 !important;
    &.Mui-disabled {
      background-color: rgba(0, 0, 0, 0.12) !important;
    }
  }
`
export class SearchComponent extends React.Component {
  constructor(props) {
    super(props)
    this.maxStartDate = new Date()
    this.minStartDate = this.getDateSince(new Date(), 365)
    this.minEndDate = this.props.fromDate
      ? this.props.fromDate
      : this.minStartDate
    this.maxEndDate = new Date()
    this.validDate = true
  }

  getDateSince = (date, noOfDays) => {
    const dateOffset = (noOfDays - 1) * 24 * 60 * 60 * 1000
    return new Date(date.setTime(date.getTime() - dateOffset))
  }

  handleCompanyCodeChange = event => {
    this.props.handleChange("companyCode", event.target.value)
  }

  handleStartDateChange = fromDate => {
    this.props.handleChange("fromDate", fromDate)
  }

  handleEndDateChange = toDate => {
    this.props.handleChange("toDate", toDate)
  }

  onSearch = () => {
    this.props.onSearch()
  }

  componentDidMount() {
    this.props.getAllCompanies()
  }
  render() {
    const { allCompanies, companyCode, fromDate, toDate } = this.props
    console.log("fdate", fromDate, toDate)
    const showTimeFormat = {
      showTimeSelect: true,
      timeFormat: "HH:mm",
      timeIntervals: 15,
      timeCaption: "Time",
    }
    return (
      <>
        <DisplayText>Search stock price by</DisplayText>
        <Grid container spacing={24}>
          <StyledGrid item xs={12} md={2} sm={6}>
            <DisplayText>Company Code</DisplayText>
            <Select
              id="company-dropdown"
              onChange={this.handleCompanyCodeChange}
              value={companyCode}
              style={{ width: "100%" }}
            >
              <MenuItem value={10}>Ten</MenuItem>
              <MenuItem value={20}>Twenty</MenuItem>
              <MenuItem value={30}>Thirty</MenuItem>
              {allCompanies.map((company, index) => (
                <MenuItem value={company.companyCode} key={index}>
                  {company.companyCode}
                </MenuItem>
              ))}
            </Select>
          </StyledGrid>
          <StyledGrid item xs={12} md={2} sm={6}>
            <DisplayText>From Date</DisplayText>
            <DatePicker
              name="fromDate"
              selectsEnd
              startDate={fromDate}
              endDate={toDate}
              placeholderText="DD/MM/YYYY HH:MM"
              dateFormat="dd/MM/yyyy HH:mm"
              selected={fromDate}
              onChange={this.handleStartDateChange}
              onKeyDown={ev => {
                ev.preventDefault()
              }}
              {...showTimeFormat}
              minDate={this.minStartDate}
              maxDate={this.maxStartDate}
              autoComplete="off"
            />
          </StyledGrid>
          <StyledGrid item xs={12} md={2} sm={6}>
            <DisplayText>To Date</DisplayText>
            <DatePicker
              name="toDate"
              selectsEnd
              startDate={fromDate}
              endDate={toDate}
              placeholderText="DD/MM/YYYY HH:MM"
              dateFormat="dd/MM/yyyy HH:mm"
              selected={toDate}
              onChange={this.handleEndDateChange}
              onKeyDown={ev => {
                ev.preventDefault()
              }}
              {...showTimeFormat}
              minDate={this.minEndDate}
              maxDate={this.maxEndDate}
              autoComplete="off"
            />
          </StyledGrid>
          <StyledGrid
            item
            xs={12}
            md={2}
            sm={6}
            style={{ alignSelf: "center" }}
          >
            <StyledBtn
              variant="contained"
              color="primary"
              onClick={this.onSearch}
              disabled={!(companyCode && fromDate && toDate)}
            >
              Search
            </StyledBtn>
          </StyledGrid>
        </Grid>
      </>
    )
  }
}

export const mapStateToProps = state => ({
  allCompanies: state.company.allCompanies,
  companyCode: state.company.companyCode,
  fromDate: state.company.fromDate,
  toDate: state.company.toDate,
})

export default connect(mapStateToProps, {
  getAllCompanies,
  handleChange,
  onSearch,
})(SearchComponent)
