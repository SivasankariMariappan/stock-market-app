import React from "react"
import logo from "../images/logo.png"
import styled from "styled-components"
import Typography from "@material-ui/core/Typography"
import SearchComponent from "./search-component"
import TableComponent from "./table-component"

const Container = styled.div`
  display: flex;
  flex-direction: column;
  > * {
    margin-bottom: 15px;
    margin-left: 10px !important;
  }
`
const FlexRow = styled.div`
  display: flex;
  flex-direction: row;
  background-color: #0064d2;
`

const Logo = styled.img`
  height: 50px;
  margin: 5px;
  border-radius: 5px;
`

const Heading = styled(Typography)`
  font-size: 22px;
  flex: 1;
  text-align: center;
  color: white;
  align-self: center;
`
export class DashboardComponent extends React.Component {
  handleChange = (event, value) => {
    this.props.onTabChange(value)
  }

  render() {
    return (
      <Container>
        <FlexRow>
          <Logo src={logo}></Logo>
          <Heading variant="h4">Stock Market</Heading>
        </FlexRow>
        <SearchComponent />
        <TableComponent />
      </Container>
    )
  }
}

export default DashboardComponent
