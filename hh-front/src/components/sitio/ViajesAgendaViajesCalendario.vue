<template>
  <FullCalendar :options='calendarOptions' />
</template>

<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data: function () {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin],
        initialView: 'dayGridMonth',
        weekends: false,
        events: [
          { title: 'Meeting', start: new Date() }
        ]
      }
    }
  }
}
</script>
